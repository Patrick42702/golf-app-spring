"""
THIS SCRIPT IS DESIGNED TO INSERT PUBLIC DATA PULLED FROM
WWW.POI-FACTORY.COM AND INSERT IT INTO A MYSQL DATABASE. IT IS
PUBLIC DATA FOR GOLF COURSES IN THE USA.
"""

import sys

import mysql.connector
import pandas as pd
from mysql.connector import Error
from pandas import DataFrame


def parse_golf(filename=""):
    if not filename.lower().endswith('.csv'):
        raise ValueError("The provided file is not a CSV file. Please provide a file with a .csv extension.")
    try:
        df:DataFrame = pd.read_csv(filename)
    except FileNotFoundError:
        raise FileNotFoundError(f"The file {filename} was not found.")
    except Exception as e:
        raise Exception(f"An error occurred while reading the file: {str(e)}")

    return df

def insert_data(db_url, user, password, table_name, df):
    connection = None
    cursor = None
    rows_inserted = 0

    try:
        if ':' in db_url:
            host, port = db_url.split(':')
            port = int(port)
        else:
            host = db_url
            port = 3306

        connection = mysql.connector.connect(
                host=host,
                port=port,
                user=user,
                password=password,
                database='mydatabase'
            )

        if connection.is_connected():
            cursor = connection.cursor()

            columns = ("NAME", "ADDRESS", "ZIP", "LONGITUDE", "LATITUDE",
                       "CLASSIFICATION", "STATE_ABBR", "CITY", "PHONE")
            columns_str = ', '.join([f'`{col}`' for col in columns])
            placeholders = ', '.join(['%s'] * len(columns))
            insert_query = f"""
            INSERT INTO `{table_name}` ({columns_str})
            VALUES ({placeholders})
            """

            data_to_insert = []
            for x in df.to_numpy():
                try:
                    name = x[2]
                    info = x[3]
                    split_name = name.split(',')
                    course_name = split_name[0]
                    state = split_name[1]
                    split_info = info.split(',')
                    classification = split_info[0]
                    address = split_info[1]
                    city = split_info[2]
                    state_zip = split_info[3]
                    state, zip = tuple(state_zip.split(' '))
                    phone = split_info[4]
                    l = [course_name, address.strip(), zip, x[0], x[1], classification, state, city.strip(), phone.strip()]
                    data_to_insert.append(tuple(l))
                except Exception as e:
                    print(e)
                    # print("This golf course is missing information: ", name)

            # Execute the query for each row
            cursor.executemany(insert_query, data_to_insert)
            rows_inserted = cursor.rowcount

            # Commit the transaction
            connection.commit()

            return rows_inserted

    except Error as e:
        print(f"Error while connecting to MySQL: {e}")
        if connection:
            connection.rollback()
        raise
    finally:
        if cursor:
            cursor.close()
        if connection and connection.is_connected():
            connection.close()

    pass


if __name__ == "__main__":
    db = "localhost:3306"
    user = "myuser"
    password = "secret"
    database_name = "mydatabase"
    table = "golf_courses"
    df = parse_golf("Golf-Courses-USA.csv")
    insert_data(db, user, password, table, df)
    pass
