import json
import unittest
import random

from configuration_manager import read_configuration, save_configuration


class ConfigurationReaderTestCase(unittest.TestCase):

    def test_should_read_configuration(self):
        self.assertIsNotNone(read_configuration("config"))

    def test_should_read_configuration_and_get_value(self):
        self.assertEqual("1", read_configuration("config").get_value("item.value", "0"))

    def test_should_read_configuration_and_get_default_value(self):
        self.assertEqual("0", read_configuration("config").get_value("item.other_value", "0"))

    def test_should_read_configuration_and_get_list_value(self):
        self.assertEqual(["1", "2", "3"], read_configuration("config").get_value("item.list", []))

    def test_should_read_configuration_and_get_default_list_value(self):
        self.assertEqual(["1"], read_configuration("config").get_value("item.other_list", ["1"]))

    def test_should_read_configuration_and_get_dictionary_value(self):
        self.assertEqual({"sample": "3"}, read_configuration("config").get_value("value", {"sample": "0"}))

    def test_should_read_configuration_and_get_default_dictionary_value(self):
        self.assertEqual({"sample": "0"}, read_configuration("config").get_value("other_value", {"sample": "0"}))

    def test_should_read_configuration_and_cache_config(self):
        configuration_one = read_configuration('config', use_cache=True)
        configuration_one = read_configuration('config', use_cache=True)

    def test_should_save_new_cached_configuration(self):
        read_configuration('config_modify', use_cache=True)
        value = random.randint(1, 100)
        json_config = json.dumps({"new": str(value)}, indent=2)
        save_configuration("config_modify", json_config)
        configuration_one = read_configuration('config_modify', use_cache=True)
        self.assertEqual(str(value), configuration_one.get_value("new"))


if __name__ == '__main__':
    unittest.main()
