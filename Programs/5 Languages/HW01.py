import math

print ('Hello, World!')

num = int(input("Enter a number: "))
print ("You entered: ", num)

if num > 1:
   for i in range(2,num):
       if (num % i) == 0:
           print(num," is not a prime number")
           print(i,"times",num//i,"is",num)
           break
   else:
       print (num," is a prime number")
else:
   print (num," is not a prime number")

equate = num ** 2 + ((math.log10(num) - math.sin(num))/ math.sqrt(num))
print ("The equated expression is: ", equate)