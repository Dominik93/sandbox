import unittest

from lists import find_item, partition, flat


class ListsTestCase(unittest.TestCase):
    def test_should_flat_items(self):
        items_of_items = [["1"], ["2", "3"]]
        items = flat(items_of_items)
        self.assertEqual(["1", "2", "3"], items)

    def test_should_find_item(self):
        items = ["1", "2", "3"]
        item = find_item(items, lambda x: x == "2")
        self.assertEqual("2", item.get())

    def test_should_partition_array(self):
        items = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]
        partition_items = partition(items, 3)
        self.assertEqual([["1", "4", "7"],
                          ["2", "5", "8"],
                          ["3", "6", "9"]], partition_items)

    def test_should_partition_array_when_size_is_not_divider_of_items_length(self):
        items = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"]
        partition_items = partition(items, 3)
        self.assertEqual([["1", "4", "7", "10"],
                          ["2", "5", "8"],
                          ["3", "6", "9"]], partition_items)

    def test_should_partition_array_when_size_is_smaller_than_items_length(self):
        items = ["1", "2", "3"]
        partition_items = partition(items, 5)
        self.assertEqual([["1", "2", "3"]], partition_items)


if __name__ == '__main__':
    unittest.main()
