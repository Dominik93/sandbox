import json

from utils.dictionary import Dictionary

__cached_configs = {}


def read_configuration(name: str, factory_provider=lambda x: Dictionary(x), use_cache=False):
    global __cached_configs
    if use_cache and name in __cached_configs:
        return __cached_configs[name]

    with open(name + ".json", 'r', encoding="utf-8") as file:
        config = factory_provider(json.load(file))
        if use_cache:
            __cached_configs[name] = config
        return config


def save_configuration(name: str, content: str):
    global __cached_configs
    with open(name + ".json", 'w', encoding="utf-8") as file:
        file.write(content)
        __cached_configs = {}
