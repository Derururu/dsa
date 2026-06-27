def countingsort (A,B,n,k):
    for i in range(0,k):
        C[i]=0
    for j in range(1,n):
        C[A[j]] += 1
    for i in range(1,k):
        C[i] += C[i-1]
    for j in range(n-1,0,-1):
        B[C[A[j]]]=A[j]
        C[A[j]] -= 1

if __name__ == "__main__":
    def run_test(test_name, input_arr):
        n = len(input_arr)
        k = max(input_arr) if n > 0 else 0
        sorted_arr = [0] * n
        countingsort(input_arr, sorted_arr, n, k)
        print(f"{test_name}:")
        print(f"  Input:  {input_arr}")
        print(f"  Output: {sorted_arr}")
        print(f"  Valid:  {sorted_arr == sorted(input_arr)}")
        print("-" * 30)

    # Test cases
    run_test("Standard Case", [4, 2, 2, 8, 3, 3, 1])
    run_test("Already Sorted", [1, 2, 3, 4, 5])
    run_test("Reverse Sorted", [5, 4, 3, 2, 1])
    run_test("Identical Elements", [7, 7, 7, 7])
    run_test("Single Element", [42])
    run_test("Large Range", [10, 0, 5, 2, 0])




