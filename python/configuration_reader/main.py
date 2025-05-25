from configuration_reader import read_configuration

if __name__ == "__main__":
    print("string:")
    print(read_configuration('config').get_value("item.value", "0"))
    print(read_configuration('config').get_value("item.other_value", "0"))
    print("\nlist:")
    print(read_configuration('config').get_value("item.list", []))
    print(read_configuration('config').get_value("item.other_list", ["1"]))
    print("\ndict:")
    print(read_configuration('config').get_value("value", {"sample": "0"}))
    print(read_configuration('config').get_value("other_value", {"sample": "0"}))
    print("\ncache:")
    print(read_configuration('config', use_cache=True).get_value("item.value", "0"))
    print(read_configuration('config', use_cache=True).get_value("item.value", "0"))
    print("\nreturn config:")
    print(read_configuration('config').get("item.obj", {"value": "0"}).get_value("value"))
    print(read_configuration('config').get("item.other_obj", {"other_value": "0"}).get_value("other_value"))
