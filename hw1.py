import math
import sys  

    #homework1
def f2(arr,n):
    #A is a 2d array of size n*n
    for i in range(n):
        arr[i,i]=0
    for a in range(2,n):
        j= +a-1
        arr[i,j]= math.inf
        for k in range(i,j-1):
            arr[i,j]= min(arr[i,j],arr[i,k]+arr[k+1,j]+arr[i]*arr[k+1]*arr[j+1])
    return arr[1,n]


def insertionRec(arr, n):
    if n <= 0:
        return
    
    insertionRec(arr, n - 1)
    key = arr[n]
    j = n - 1
    while j >= 0 and arr[j] > key:
        arr[j + 1] = arr[j]
        j -= 1
    arr[j + 1] = key

if __name__ == "__main__":
    arr = [3, 1, 4, 2, 5]
    insertionRec(arr, len(arr) - 1)
    print("Sorted array is: ")
    for i in range(1,len(arr)):
        print(arr[i], end=" ")
    