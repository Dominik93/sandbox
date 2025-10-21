import json
import unittest
import random

from utils.dictionary import Dictionary


class DictionaryTestCase(unittest.TestCase):

    def test_should_set_new_value(self):
        dictionary = Dictionary({"sample": {"property": 1}})
        dictionary.set("sample.otherProperty", 2)
        self.assertEqual(1, dictionary.get_value("sample.property"))
        self.assertEqual(2, dictionary.get_value("sample.otherProperty"))

    def test_should_set_value(self):
        dictionary = Dictionary({"sample": {"property": 1}})
        dictionary.set("sample.property", 5)
        self.assertEqual(5, dictionary.get_value("sample.property"))

    def test_should_delete_value(self):
        dictionary = Dictionary({"sample": {"property": 1}})
        dictionary.delete("sample.property")
        self.assertEqual(False, dictionary.exists("sample.property"))


if __name__ == '__main__':
    unittest.main()
