#ifdef _WIN32
#include <winsock2.h>
#include <ws2tcpip.h>
#pragma comment(lib, "ws2_32.lib")
#else
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#endif

#include <iostream>
#include <string>
#include <queue>

// Patient structure
struct Patient
{
    std::string name;
    int priority; // 0 for Regular, 1 for Priority
};

std::queue<Patient> regularQueue;
std::queue<Patient> priorityQueue;

// Add patient to the queue
void addPatient(const std::string &name, int priority)
{
    Patient patient = {name, priority};
    if (priority == 1)
    {
        priorityQueue.push(patient);
    }
    else
    {
        regularQueue.push(patient);
    }
}

// Get the next patient from the queue
std::string getNextPatient()
{
    if (!priorityQueue.empty())
    {
        Patient p = priorityQueue.front();
        priorityQueue.pop();
        return "Priority: " + p.name;
    }
    else if (!regularQueue.empty())
    {
        Patient p = regularQueue.front();
        regularQueue.pop();
        return "Regular: " + p.name;
    }
    return "No patients in the queue.";
}

int main()
{
#ifdef _WIN32
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
    {
        std::cerr << "WSAStartup failed." << std::endl;
        return 1;
    }
#endif

    int server_fd;
    struct sockaddr_in address;
    int addrlen = sizeof(address);

#ifdef _WIN32
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
#else
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
#endif

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(8080);

#ifdef _WIN32
    bind(server_fd, (struct sockaddr *)&address, sizeof(address));
    listen(server_fd, 3);
#else
    bind(server_fd, (struct sockaddr *)&address, sizeof(address));
    listen(server_fd, 3);
#endif

    std::cout << "Waiting for connection..." << std::endl;

    while (true)
    {
        int new_socket;
#ifdef _WIN32
        new_socket = accept(server_fd, (struct sockaddr *)&address, &addrlen);
#else
        new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t *)&addrlen);
#endif

        char buffer[1024] = {0};
        recv(new_socket, buffer, 1024, 0);
        std::string input(buffer);

        if (input.find("ADD") == 0)
        {
            size_t delim = input.find(":");
            std::string name = input.substr(4, delim - 4);
            int priority = std::stoi(input.substr(delim + 1));
            addPatient(name, priority);
            send(new_socket, "Patient added.\n", 15, 0);
        }
        else if (input == "NEXT")
        {
            std::string nextPatient = getNextPatient();
            send(new_socket, nextPatient.c_str(), nextPatient.size(), 0);
        }

#ifdef _WIN32
        closesocket(new_socket);
#else
        close(new_socket);
#endif
    }

#ifdef _WIN32
    WSACleanup();
#endif

    return 0;
}
