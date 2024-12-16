import React, {useState, useEffect} from "react";
import "tailwindcss/tailwind.css";
import CodeEditor from "../Component/CodeEditor";
import {MonacoEditor} from '@monaco-editor/react';
import { useParams } from "react-router-dom";




const ProblemDescriptionPart = () => {

  const [problemDescription, setProblemDescription] = useState(null)
  const {problemId} = useParams();

    useEffect(() => {
        fetch(`http://localhost:5000/problems/${problemId}`)
        .then((response) => {
            if (!response.ok){
                throw new Error("Failed to fetch problem description")
            }
            return response.json();
        })
        .then((data) => {
            setProblemDescription(data);
        })
        .catch((error) => {
            console.error("Error fetching problem description", error);
        });
    }, []); //Dependency array ensures this runs only once
    return (
        <div className="bg-white p-6 shadow-md border-r border-gray-200">
          <h2 className="text-2xl font-bold text-blue-600 mb-4">Problem Description</h2>
          {problemDescription ? (
            <>
              <p className="text-gray-900 mb-4 font-bold text-xl">
                {problemDescription.title}
              </p>
              <p className="text-gray-700 mb-4">{problemDescription.description}</p>
              <pre className="bg-gray-50 p-4 rounded-md text-sm border border-gray-200">
                {problemDescription.example}
              </pre>
              <div className="mt-4 text-left">
                <span className="text-gray-800 font-bold"> Contsraints</span>
              <pre className="bg-gray-50 p-4 rounded-md text-sm border border-gray-200">
                {problemDescription.constraints}
              </pre>
              </div>
            </>
          ) : (
            <p className="text-gray-500">Loading problem description...</p>
          )}
        </div>
      );
    };

export default ProblemDescriptionPart;
