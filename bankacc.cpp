#include <iostream>
#include <fstream>
#include <limits>
#include <string>
#include <vector>
#include<conio.h>

using namespace std;

struct Account {
    string name;
    int long long accountNumber;
    float balance;
};

vector<Account> accounts;

void loadAccounts() {
    ifstream inFile;
    inFile.open("accounts.txt", ios::in);

    Account account;

    while (inFile >> account.name >> account.accountNumber >> account.balance) {
        accounts.push_back(account);
    }

    inFile.close();
}

void saveAccounts() {
    ofstream outFile;
    outFile.open("accounts.txt", ios::app);

    for (const auto& account : accounts) {
        outFile << account.name << " " << account.accountNumber << " " << account.balance << endl;
    }

    outFile.close();
}

void createAccount() {
    Account account;

	cout<<endl;
    cout << "Enter account holder's name: ";
    cin.ignore();  // Ignore the newline character left in the input stream
    getline(cin, account.name);

    cout << "Enter account number: ";
    cin >> account.accountNumber;

    cin.clear();
   
	for (const auto& acc : accounts) {
			if(acc.accountNumber == account.accountNumber)
			{
				cout<<"Sorry, this account already existed!!!";
				return;
			}
	}
    cout << "Enter initial balance: ";
    cin >> account.balance;

    accounts.push_back(account);
    saveAccounts();

    cout << "Account created successfully!" << endl;
    cout<<endl;
}


void deposit() {
    int accNumber;
    float amount;
    bool found = false;
	
	cout<<endl;
    cout << "Enter account number: ";
    cin >> accNumber;
    cout << "Enter amount to deposit: ";
    cin >> amount;

    for (auto& account : accounts) {
        if (account.accountNumber == accNumber) {
            account.balance += amount;
            found = true;
            break;
        }
    }

    if (found) {
        saveAccounts();
        cout << "Amount deposited successfully!" << endl;
    } else {
        cout << "Account not found!" << endl;
    }
    cout<<endl;
}

void withdraw() {
    int accNumber;
    float amount;
    bool found = false;
	
	cout<<endl;
    cout << "Enter account number: ";
    cin >> accNumber;
    cout << "Enter amount to withdraw: ";
    cin >> amount;

    for (auto& account : accounts) {
        if (account.accountNumber == accNumber) {
            if (account.balance >= amount) {
                account.balance -= amount;
                found = true;
                break;
            } else {
                cout << "Insufficient balance!" << endl;
            }
        }
    }

    if (found) {
        saveAccounts();
        cout << "Amount withdrawn successfully!" << endl;
    } else {
        cout << "Account not found!" << endl;
    }
    cout<<endl;
}

void displayBalance() {
    int long long accNumber;
    bool found = false;
    
	cout << endl;
    cout << "Enter account number: ";
    cin >> accNumber;
    cin.ignore(); // Add this line to clear the newline character

    for (const auto& account : accounts) {
        if (account.accountNumber == accNumber) {
            cout << "Account Holder Name: " << account.name << endl;
            cout << "Account Number: " << account.accountNumber << endl;
            cout << "Balance: " << account.balance << endl;
            found = true;
            break;
        }
    }

    if (!found) {
        cout << "Account not found!" << endl;
    }
    
    cout << endl;
}

void closeAccount() {
    int accNumber;
    bool found = false;

	cout<<endl;
    cout << "Enter account number: ";
    cin >> accNumber;

    for (auto it = accounts.begin(); it != accounts.end(); ++it) {
        if (it->accountNumber == accNumber) {
            accounts.erase(it);
            found = true;
            break;
        }
    }

    if (found) {
        saveAccounts();
        cout << "Account closed successfully!" << endl;
    } else {
        cout << "Account not found!" << endl;
    }
    cout<<endl;
}

int main() {
    loadAccounts();

    int choice;

    do {
    	system("cls");
        cout << "Bank Account Management System" << endl;
        cout << "1. Create Account" << endl;
        cout << "2. Deposit" << endl;
        cout << "3. Withdraw" << endl;
        cout << "4. Balance Inquiry" << endl;
        cout << "5. Close Account" << endl;
        cout << "0. Exit" << endl;
        cout<<endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                displayBalance();
                break;
            case 5:
                closeAccount();
                break;
            case 0:
                cout << "Thank you for using the program. Goodbye!" << endl;
                break;
            default:
                cout << "Invalid choice. Please try again." << endl;
                break;
        }
    cout<<"\n\nPress any key";

        getch();
    } while (choice != 0);

    return 0;
}

