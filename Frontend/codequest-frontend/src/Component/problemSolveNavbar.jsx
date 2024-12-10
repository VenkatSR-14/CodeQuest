import React from "react";
import "tailwindcss/tailwind.css";
import CodeEditor from "../Component/CodeEditor";

const navigation = [
  { name: "Problem Set", href: "#" },
  { name: "Interview", href: "#" },
  { name: "Study", href: "#" },
];

// const ProblemPage = () => {
//   return (
//     <div className="min-h-screen bg-gradient-to-br from-indigo-100 via-purple-100 to-white flex flex-col">
//       {/* Header */}
//       <header className="bg-gradient-to-r from-blue-500 to-purple-500 text-white p-4 flex justify-between items-center shadow-md">
//         <h1 className="text-3xl font-extrabold tracking-wide">CodeQuest</h1>
//         <div className="flex gap-4">
//           <input
//             type="text"
//             placeholder="Search for problems..."
//             className="rounded-md p-2 text-gray-700 w-80 shadow-md border border-gray-300"
//           />
//           <button className="px-4 py-2 bg-white text-blue-600 font-semibold rounded-md shadow hover:bg-blue-100">
//             Register →
//           </button>
//           <button className="px-4 py-2 bg-white text-blue-600 font-semibold rounded-md shadow hover:bg-blue-100">
//             Login →
//           </button>
//         </div>
//       </header>

//       {/* Main Content */}
//       <div className="flex flex-1 mt-6 px-8 gap-8">
//         {/* Problem Description Panel */}
//         <div className="w-2/5 bg-white rounded-lg shadow-lg p-6 border border-gray-200">
//           <h2 className="text-2xl font-bold text-blue-600 mb-4">Maximum Number of Integers to Choose</h2>
//           <p className="text-gray-700 mb-4">
//             Given an array of positive integers <code>banned</code> and two integers <code>size</code> and <code>maxSum</code>,
//             select integers satisfying the following rules:
//           </p>
//           <ul className="list-disc list-inside mb-4 text-gray-600">
//             <li>Integers in the range [1, size] may be selected.</li>
//             <li>Each integer may be selected at most once.</li>
//             <li>Integers in <code>banned</code> cannot be selected.</li>
//             <li>The sum of selected integers cannot exceed <code>maxSum</code>.</li>
//           </ul>
//           <p className="text-gray-700">
//             <strong>Example:</strong>
//             <pre className="bg-gray-50 p-4 rounded-md text-sm border border-gray-200">
//               Input: banned = [1, 2], size = 4, maxSum = 6{`\n`}
//               Output: 2{`\n`}
//               Explanation: You can select 3 and 4.
//             </pre>
//           </p>
//         </div>

//         {/* Code Editor Panel */}
//         <div className="w-3/5 bg-white rounded-lg shadow-lg p-6 border border-gray-200">
//           <h2 className="text-lg font-bold text-blue-600 mb-4">Your Code</h2>
//           <CodeEditor />
//           <div className="mt-6 flex justify-end gap-4">
//             <button className="px-4 py-2 bg-blue-500 text-white rounded-md shadow-md font-semibold hover:bg-blue-600">
//               Run Test
//             </button>
//             <button className="px-4 py-2 bg-green-500 text-white rounded-md shadow-md font-semibold hover:bg-green-600">
//               Submit
//             </button>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default ProblemPage;

const ProblemSolveNavbar = () => {
  return (
    <div className="max-h-screen flex items-center justify-between p-3 lg:px-8 bg-indigo-600">
      {/* First element on the left */}
      <span className="font-bold text-2xl text-white">CodeQuest</span>

      {/* Second element in the center */}
      <div className="hidden lg:flex lg:gap-x-12 text-gray-100">
        {navigation.map((item) => (
          <a
            key={item.name}
            href={item.href}
            className="text-sm font-semibold text-white"
          >
            {item.name}
          </a>
        ))}
      </div>

      {/* Last elements on the right with spacing */}
      <div className="flex items-center space-x-4">
        <div className="relative rounded-full px-3 py-1 text-sm text-white ring-1 ring-white hover:bg-indigo-600 hover:ring-white">
          <a href="/register" className="font-semibold text-white">
            Register <span aria-hidden="true">&rarr;</span>
          </a>
        </div>
        <div className="relative rounded-full px-3 py-1 text-sm text-white ring-1 ring-white hover:bg-indigo-600 hover:ring-white">
          <a href="/login" className="font-semibold text-white">
            Login <span aria-hidden="true">&rarr;</span>
          </a>
        </div>
      </div>
    </div>
    
  );
};

export default ProblemSolveNavbar;
