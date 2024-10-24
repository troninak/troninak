#include <iostream>

class MyClass {
public:
    void myMethod() {
        std::cout << "Hello, GitHub!" << std::endl;
    }
};

int main() {
    MyClass obj;
    obj.myMethod();
    return 0;
}