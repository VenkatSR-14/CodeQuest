import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import ProblemDescriptionPart from "../Component/problemDescription";
import CodeEditor from "../Component/CodeEditor";
import ProblemSolveNavbar from "../Component/problemSolveNavbar";
import { FaClock } from "react-icons/fa";
import Discussions from "../Component/Discussions";

const ProblemPage = () => {
  const { problemName } = useParams(); // Get the dynamic problemName
  const [dividerPosition, setDividerPosition] = useState(50); // Divider starts at 50%
  const [timer, setTimer] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => setTimer((prev) => prev + 1), 1000);
    return () => clearInterval(interval);
  }, []);

  // Convert seconds to MM:SS
  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60).toString().padStart(2, "0");
    const secs = (seconds % 60).toString().padStart(2, "0");
    return `${mins}:${secs}`;
  };

  // Handle Divider Drag
  const handleDrag = (e) => {
    const newDividerPosition = (e.clientX / window.innerWidth) * 100; // Get position in %
    setDividerPosition(Math.min(80, Math.max(20, newDividerPosition))); // Limit between 20% and 80%
  };

  return (
    <div className="min-h-screen flex flex-col bg-gray-100">
      {/* Navbar */}
      <ProblemSolveNavbar />

      {/* Timer */}
      <div className="flex justify-center items-center text-indigo-600 py-2">
        <FaClock className="mr-2 text-xl" />
        <span className="font-semibold">Elapsed Time:</span>
        <span className="ml-2">{formatTime(timer)}</span>
      </div>

      {/* Main Content */}
      <div className="flex flex-row flex-1">
        {/* Problem Description */}
        <div
          className="bg-white shadow-md border-r border-gray-200 p-6"
          style={{ width: `${dividerPosition}%` }} // Use dynamic width
        >
          <ProblemDescriptionPart />
          <Discussions/>
        </div>

        {/* Divider */}
        <div
          className="bg-gray-300 w-2 cursor-col-resize"
          onMouseDown={(e) => {
            e.preventDefault();
            window.addEventListener("mousemove", handleDrag);
            window.addEventListener("mouseup", () => {
              window.removeEventListener("mousemove", handleDrag);
            });
          }}
        ></div>

        {/* Code Editor */}
        <div
          className="bg-white shadow-md border-l border-gray-200 p-6"
          style={{ width: `${100 - dividerPosition}%` }} // Use dynamic width
        >
          <h2 className="text-lg font-bold text-blue-600 mb-4">Your Code</h2>
          <CodeEditor />
          <div className="mt-6 flex justify-end gap-4">
            <button className="px-4 py-1 bg-green-500 text-white rounded-full border-2 border-green-500 shadow-md font-semibold hover:bg-green-600 transition">
              Run Test
            </button>
            <button className="px-4 py-1 bg-indigo-500 text-white rounded-full border-2 border-indigo-500 shadow-md font-semibold hover:bg-indigo-600 transition">
              Submit
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProblemPage;
