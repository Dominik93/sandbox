class Pair:

    def __init__(self, left, right):
        self.right = right
        self.left = left

    def get(self):
        if self.left is None and self.right is None:
            raise Exception("Left and right are None")
        if self.left is not None and self.right is not None:
            raise Exception("Left and right are not None")
        if self.left is not None:
            return self.left
        if self.right is not None:
            return self.right
