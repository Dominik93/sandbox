from csv_writer import write

if __name__ == "__main__":
    content = [{"col_1": "val.1.1", "col_2": "val.1.2"},
               {"col_1": "val.2.1", "col_2": None}]

    write("test.csv", content)
    write("test2.csv", content, headers=["col_1"])
    write("test3.csv", content, headers=["col_1", "col_2"])
    write("test4.csv", content, separator="-")
