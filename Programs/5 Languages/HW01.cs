using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HW01 {
    class Program {
        static void Main(string[] args) {
            Console.WriteLine("Hello, World");

            Console.Write("Input a number: ");
            int x = Convert.ToInt32(Console.ReadLine());
            int nCnt = 0;
            Console.WriteLine("Your entered number: " + x);

            for(int i = 1; i < x; i ++) {
                if(x % i == 0) {
                    nCnt++;
                }
            }
            
            if(nCnt > 2) {
                Console.WriteLine("Number is not prime.");
            } else {
                Console.WriteLine("Number is prime.");
            }

            double result = (x * x + ((Math.Log10(x) - Math.Sin(x)))/Math.Sqrt(x));
            Console.WriteLine(result);
            while (true) ;
        }
    }
}
