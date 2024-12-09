import { useState } from "react";
import { Dialog, DialogPanel } from "@headlessui/react";
import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";

const navigation = [
  { name: "Problem Set", href: "#" },
  { name: "Interview", href: "#" },
  { name: "Study", href: "#" },
];

const topics = {
  "Data Structures and Algorithms": [
    {
      title: "Arrays",
      description:
        "Learn about array operations such as insertion, deletion, and traversal, as well as their applications in solving real-world problems.",
    },
    {
      title: "Linked Lists",
      description:
        "Understand linked lists, including singly, doubly, and circular linked lists, and how they are used in dynamic memory allocation.",
    },
    {
      title: "Stacks",
      description:
        "Master stack operations like push and pop, explore their applications in recursion, parsing, and backtracking algorithms.",
    },
    {
      title: "Queues",
      description:
        "Dive into queue operations, including circular and priority queues, and their use in task scheduling and breadth-first search.",
    },
  ],
  "System Design": [
    {
      title: "Load Balancing",
      description:
        "Learn techniques for distributing incoming network traffic across multiple servers to ensure high availability and reliability.",
    },
    {
      title: "Caching",
      description:
        "Understand caching strategies like LRU and LFU, and how caching improves performance in distributed systems and APIs.",
    },
    {
      title: "Database Sharding",
      description:
        "Explore database sharding techniques to improve scalability and performance for large-scale distributed databases.",
    },
    {
      title: "Microservices",
      description:
        "Study the principles of microservice architecture, including API gateways, inter-service communication, and fault tolerance.",
    },
  ],
  "CS Fundamentals": [
    {
      title: "Operating Systems",
      description:
        "Understand OS concepts like process management, memory allocation, file systems, and how operating systems enable multitasking.",
    },
    {
      title: "Networking",
      description:
        "Dive into networking protocols like TCP/IP, HTTP, and DNS, and learn how data is transmitted securely over the internet.",
    },
    {
      title: "Algorithms",
      description:
        "Explore algorithm design paradigms such as divide-and-conquer, greedy algorithms, and dynamic programming for problem-solving.",
    },
    {
      title: "Compiler Design",
      description:
        "Learn about the phases of compiler design, including lexical analysis, syntax analysis, and code optimization techniques.",
    },
  ],
  DBMS: [
    {
      title: "Normalization",
      description:
        "Learn database normalization techniques to eliminate redundancy and ensure data consistency using normal forms.",
    },
    {
      title: "Transactions",
      description:
        "Understand ACID properties, transaction isolation levels, and how to ensure consistency in concurrent database operations.",
    },
    {
      title: "Indexes",
      description:
        "Explore the importance of indexing, covering B-trees and hash indexes, to improve query performance in relational databases.",
    },
    {
      title: "SQL Queries",
      description:
        "Practice writing efficient SQL queries for data retrieval, manipulation, and aggregation using advanced SQL techniques.",
    },
  ],
};

const Introduction = () => {
  const [activeTab, setActiveTab] = useState("Data Structures and Algorithms");
  const handleClickTab = (tab) => {
    setActiveTab(tab);
  };
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  return (
    <div className="bg-white min-h-screen flex flex-col">
      <header className="absolute inset-x-0 top-0 z-50">
        <nav
          aria-label="Global"
          className="flex items-center justify-between p-6 lg:px-8"
        >
          <div className="flex lg:flex-1">
            <a href="#" className="-m-1.5 p-1.5">
              <span className="sr-only">Your Company</span>
            </a>
          </div>
          <div className="flex lg:hidden">
            <button
              type="button"
              onClick={() => setMobileMenuOpen(true)}
              className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700"
            >
              <span className="sr-only">Open main menu</span>
              <Bars3Icon aria-hidden="true" className="size-6" />
            </button>
          </div>
          <div className="hidden lg:flex lg:gap-x-12 text-gray-100">
            {navigation.map((item) => (
              <a
                key={item.name}
                href={item.href}
                className="text-sm/6 font-semibold text-gray-900"
              >
                {item.name}
              </a>
            ))}
          </div>
          <div className="hidden lg:flex lg:flex-1 lg:justify-end">
            <div className="relative rounded-full ml-3 px-3 py-1 text-sm/6 text-gray-600 ring-1 ring-gray-900/10 hover:bg-indigo-500 hover:ring-indigo-500">
              <a
                href="/register"
                className="font-semibold text-indigo-600 hover:text-white"
              >
                <span aria-hidden="true" className="absolute inset-0" />
                Register <span aria-hidden="true">&rarr;</span>
              </a>
            </div>
            <div className="relative rounded-full ml-3 px-3 py-1 text-sm/6 text-gray-600 ring-1 ring-gray-900/10 hover:bg-indigo-500 hover:ring-indigo-500">
              <a
                href="/login"
                className="font-semibold text-indigo-600 hover:text-white"
              >
                <span aria-hidden="true" className="absolute inset-0" />
                Login <span aria-hidden="true">&rarr;</span>
              </a>
            </div>
          </div>
        </nav>
        <Dialog
          open={mobileMenuOpen}
          onClose={setMobileMenuOpen}
          className="lg:hidden"
        >
          <div className="fixed inset-0 z-50" />
          <DialogPanel className="fixed inset-y-0 right-0 z-50 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
            <div className="flex items-center justify-between">
              <a href="#" className="-m-1.5 p-1.5">
                <span className="sr-only">Your Company</span>
                <img
                  alt=""
                  src="https://tailwindui.com/plus/img/logos/mark.svg?color=indigo&shade=600"
                  className="h-8 w-auto"
                />
              </a>
              <button
                type="button"
                onClick={() => setMobileMenuOpen(false)}
                className="-m-2.5 rounded-md p-2.5 text-gray-700"
              >
                <span className="sr-only">Close menu</span>
                <XMarkIcon aria-hidden="true" className="size-6" />
              </button>
            </div>
            <div className="mt-6 flow-root">
              <div className="-my-6 divide-y divide-gray-500/10">
                <div className="space-y-2 py-6">
                  {navigation.map((item) => (
                    <a
                      key={item.name}
                      href={item.href}
                      className="-mx-3 block rounded-lg px-3 py-2 text-base/7 font-semibold text-gray-900 hover:bg-gray-50"
                    >
                      {item.name}
                    </a>
                  ))}
                </div>
                <div className="py-6">
                  <a
                    href="/login"
                    className="-mx-3 block rounded-lg px-3 py-2.5 text-base/7 font-semibold text-gray-900 hover:bg-gray-50"
                  >
                    Log in
                  </a>
                </div>
              </div>
            </div>
          </DialogPanel>
        </Dialog>
      </header>

      <div className="relative isolate px-6 pt-14 lg:px-8">
        <div
          aria-hidden="true"
          className="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80"
        >
          <div
            style={{
              clipPath:
                "polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)",
            }}
            className="relative left-[calc(50%-11rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-30rem)] sm:w-[72.1875rem]"
          />
        </div>
        <div className="mx-auto max-w-2xl py-32 sm:py-15 lg:py-1">
          <div className="hidden sm:mb-8 sm:flex sm:justify-center"></div>
          <div className="text-center">
          <h1 className="text-5xl text-indigo-600 font-bold">CodeQuest</h1>
            <h2 className="text-balance my-5 text-2xl font-semibold tracking-tight text-gray-900 sm:text-4xl">
              Level Up Your Coding Skills: Learn, Practice, and Compete
            </h2>
            <p className="mt-8 text-pretty text-lg font-medium text-gray-500 sm:text-xl/6">
              Master programming concepts with in-depth learning materials,
              prepare for your dream job with curated interview challenges, and
              test your skills by competing with coders worldwide. Your journey
              to coding excellence starts here
            </p>
          </div>
        </div>
        <div
          aria-hidden="true"
          className="absolute inset-x-0 top-[calc(100%-13rem)] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[calc(100%-30rem)]"
        >
          <div
            style={{
              clipPath:
                "polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)",
            }}
            className="relative left-[calc(50%+3rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%+36rem)] sm:w-[72.1875rem]"
          />
        </div>
        <div>
          <div className="sm:hidden">
            <label htmlFor="Tab" className="sr-only">
              Tab
            </label>

            <select
              id="Tab"
              className="w-full rounded-md border-gray-200 cursor-pointer"
              value={activeTab}
              onClick={(e) => handleClickTab(e.target.value)}
            >
              <option selected>Data Structures and Algorithms</option>
              <option>System Design</option>
              <option>Computer Science fundamentals</option>
              <option>DBMS</option>
            </select>
          </div>

          <div className="hidden sm:block mt-5 flex justify-center items-center">
            <div className="border-b border-gray-200">
              <nav className="flex justify-center gap-20">
                {Object.keys(topics).map((tab) => (
                  <button
                    key={tab}
                    onClick={() => setActiveTab(tab)}
                    className={`shrink-0 border p-4 text-sm font-medium ${
                      activeTab === tab
                        ? "border-gray-300 border-b-white text-indigo-600 rounded-t-lg"
                        : "border-transparent text-black-500 hover:text-gray-700"
                    }`}
                  >
                    {tab}
                  </button>
                ))}
              </nav>
            </div>
            {/*Dynamic Grid section*/}
            <div className="mt-10 grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 px-6">
              {topics[activeTab].map((concept) => (
                <div
                  key={concept.title}
                  className="rounded-lg border border-gray-200 p-5 shadow hover:shadow-lg transition-shadow duration-300"
                >
                  <a href={"/"+concept.title} className="text-lg font-semibold text-indigo-600">
                    {concept.title}
                  </a>
                  <p className="mt-2 text-sm text-gray-600">
                    {concept.description}
                  </p>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Introduction;
