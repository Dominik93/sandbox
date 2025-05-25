from configuration_reader import read_configuration

if __name__ == "__main__":
    print(read_configuration('config').get("item.value", "0"))
    print(read_configuration('config').get("item.other_value", "0"))
    print(read_configuration('config').get("item.list", []))
    print(read_configuration('config').get("item.other_list", ["1"]))
    print(read_configuration('config').get("value", {"sample": "0"}))
    print(read_configuration('config').get("other_value", {"sample": "0"}))

    print(read_configuration('config', use_cache=True).get("item.value", "0"))
    print(read_configuration('config', use_cache=True).get("item.value", "0"))
