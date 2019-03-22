#include <iostream>
#include <time.h>
#include <sys/time.h>
using namespace std;

struct Results {
	const char* const* Words;												// pointers to unique found words, each terminated by a non-alpha char
	unsigned           Count;												// number of words found
	unsigned           Score;												// total score
	void*              UserData;											// ignored
};

void LoadDictionary(const char* path) {

}
void FreeDictionary() {

}

Results FindWords(const char* board, unsigned width, unsigned height) {

}
void FreeWords(Results results) {

}
int main(int args, char** argv) {
    cout << "Boggle Game " << endl;


    return 0;
}