from pair import Pair

if __name__ == "__main__":
    print(Pair("left", None).get())

    print(Pair(None, "right").get())

    Pair(lambda x: print("left " + x), None).get()("x")

    print(Pair(None, None).get())
    print(Pair("left", "right").get())
