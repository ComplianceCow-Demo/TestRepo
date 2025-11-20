def count_lines(path):
    with open(path, "r") as f:
        return sum(1 for _ in f)

print(count_lines("Test.txt"))
