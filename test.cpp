// #include <iostream> 
// using namespace std;

// class Dog {
//     public:
//         string name;
//         int type;

//         void bark(){
//             cout<<"Woof! "<< name << endl;
//         }
// };

// int main() {

//     Dog myDawg;
//     myDawg.name = "jumbo";
//     myDawg.bark();

//     cout << "Hello, World!" << endl;
//     return 0;
// }

// #include <iostream>

// int global_value = 100;

// // This function returns a reference to the global variable.
// int& getGlobalValue() {
//     return global_value;
// }

// int main() {
//     std::cout << "Initial global value: " << global_value << std::endl;

//     // Call the function and assign its return value to a variable.
//     int normal_copy = getGlobalValue();
//     normal_copy = 50; // This only changes the copy, not the original.
//     std::cout << "Global value after changing copy: " << global_value << std::endl;

//     // Use the function on the LEFT side of an assignment.
//     // Because it returns a reference, this will change the original global variable.
//     getGlobalValue() = 10;
//     std::cout << "Global value after return-by-reference assignment: " << global_value << std::endl;

//     return 0;
// }

// #include <iostream>

// // Suggesting to the compiler that this function be inlined.
// inline int cube(int s) {
//     return s * s * s;
// }

// int main() {
//     // The compiler may replace this line with: std::cout << (5 * 5 * 5);
//     std::cout << "The cube of 5 is: " << cube(5) << std::endl;

//     // And this line with: std::cout << (10 * 10 * 10);
//     std::cout << "The cube of 10 is: " << cube(10) << std::endl;

//     return 0;
// }

// #include <iostream>

// // Base Class
// class Shape {
// public:
//     // By marking draw() as virtual, we enable polymorphism.
//     virtual void draw() {
//         std::cout << "Drawing a generic shape." << std::endl;
//     }
// };

// // Derived Classes
// class Circle : public Shape {
// public:
//     // Circle provides its own specific version of draw().
//     void draw() override { // 'override' confirms we're overriding a base virtual function
//         std::cout << "Drawing a circle: O" << std::endl;
//     }
// };

// class Square : public Shape {
// public:
//     // Square also provides its own version.
//     void draw() override {
//         std::cout << "Drawing a square: []" << std::endl;
//     }
// };

// void performDraw(Shape* shape_ptr) {
//     // This single line can call different functions at runtime!
//     shape_ptr->draw();
// }

// int main() {
//     Circle my_circle;
//     Square my_square;

//     performDraw(&my_circle); // Calls Circle::draw()
//     performDraw(&my_square); // Calls Square::draw()

//     return 0;
// }


// #include <iostream>

// class BankAccount {
// private:
//     std::string owner_name;
//     double balance;

// public:
//     BankAccount(std::string name, double amount) : owner_name(name), balance(amount) {}

//     // The BankAccount class declares that printStatement is its friend.
//     friend void printStatement(const BankAccount account);
// };

// // printStatement is a REGULAR function, NOT a member of BankAccount.
// // But because it's a friend, it can access private members like 'balance'.
// void printStatement(const BankAccount account) {
//     std::cout << "--- Bank Statement ---" << std::endl;
//     std::cout << "Account Holder: " << account.owner_name << std::endl;
//     std::cout << "Current Balance: $" << account.balance << std::endl; // Accessing private data!
// }

// int main() {
//     BankAccount my_account("Aditi Sharma", 50000.0);
//     printStatement(my_account); // Calling the friend function
//     return 0;
// }

// #include <iostream>
// using namespace std;

// class test{

//     private:
//         int arr[60];

//     public:
//         test(){
//             for(int i=0; i< 60; i++){
//                 arr[i]= 0;
//             }
//         }    

//         int& operator[](const int i){
//             if(i<0 || i>=60){
//                 cout<<"Index out of bounds"<<endl;
//                 exit(1);
//             }
//             return arr[i];
//         }

// };

// int main() {
//     test t1;
//     t1[0] = 100;
//     cout<<t1[0]<<endl;
//     t1[500] = 1000;
//     return 0;
// }

#include <iostream>
#include <cstring>
using namespace std;

class String {
    string s;
public:
    String(string s = "") {
        this->s = s;
    }

    String operator+(const String& s) {
        string temp;
        temp = this->s + s.s;
        return String(temp);
    }

    void show() {
        cout << s << endl;
    }
};

int main() {
    String s1("Hello ");
    String s2("World");
    String s3 = s1 + s2;
    s3.show();
}
