from optional import of, empty

if __name__ == "__main__":
    print(of("value").get())
    print(of("value").or_get("other"))
    print(of("value").map(lambda x: x + " new").or_get("other"))

    print(empty().or_get("other"))
    print(empty().map(lambda x: x + " new").or_get("other"))
    print(empty().or_else_get(lambda: "other method"))
