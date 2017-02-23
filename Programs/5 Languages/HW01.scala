object HW01 {
   def main(args: Array[String]) {
      println("Hello, World!") 

      println("Input a number: ") 
      var x = Console.readInt;
      var nCnt = 0;
      var i = 0;
      println("Your entered number: " + x);

      for(i <- 1 to x) {
      	if(x % i == 0) {
      		nCnt += 1;
      	}
      }

      if(nCnt > 2) {
      	println("This number is not prime.");
      } else {
      	println("This number is prime.");
      }

      var result = (x * x)  + ((scala.math.log10(x) - scala.math.sin(x))/scala.math.sqrt(x));
      println(result);

      var hi = 5;
      var hello = 6.5;
      hi = hello;

      println(hi);
   }
}