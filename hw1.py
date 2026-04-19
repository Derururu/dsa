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


if __name__ == "__main__":
    # Everything after the script name is a command-line parameter.
    params = sys.argv[1:]
    print("Parameters:", params)

