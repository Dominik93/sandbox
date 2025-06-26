import unittest

from lists import find_item


class ListsTestCase(unittest.TestCase):

    def test_should_find_item(self):
        items = ["1", "2", "3"]
        item = find_item(items, lambda x: x == "2")
        self.assertEqual("2", item.get())


if __name__ == '__main__':
    unittest.main()
