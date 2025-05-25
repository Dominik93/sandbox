import json


def read_configuration(name):
    with open(name + ".json", 'r', encoding="utf-8") as file:
        return Config(json.load(file))


class Config:

    def __init__(self, config):
        self.config = config

    def get(self, path: str, default: any = '') -> dict | list | str | int:
        return self._get_or_default(path.split("."), default)

    def _get_or_default(self, property_names: list, default: any = '') -> dict | list | str | int:
        result = self.config
        for property_name in property_names:
            if result is not None and property_name in result:
                result = result[property_name]
            else:
                return default
        return result
