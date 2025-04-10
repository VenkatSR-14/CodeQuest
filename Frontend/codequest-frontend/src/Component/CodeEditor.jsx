import React, { useState, useEffect } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import { javascript } from '@codemirror/lang-javascript';
import { python } from '@codemirror/lang-python';
import { java } from '@codemirror/lang-java';
import { cpp } from '@codemirror/lang-cpp';
import { oneDark } from '@codemirror/theme-one-dark';
import { useParams } from 'react-router-dom';
import axios from 'axios';

// Store functions instead of invoked results
const languageOptions = {
  Javascript: javascript,
  Python: python,
  Java: java,
  Cpp: cpp,
};

const templateOptions = {
  Javascript: "// Start coding here",
  Python: "# Start coding here",
  Cpp : "// Start coding here",
  Java: "// Start coding here"
}

const CodeEditor = () => {
  const [selectedLanguage, setSelectedLanguage] = useState('Python'); // Default language
  const [code, setCode] = useState(templateOptions['Python']);
  const {problemId} = useParams();
  const [isExecuting, setIsExecuting] = useState(false);
  const [executionResult, setExecutionResult] = useState(null);
  const [error, setError] = useState(null);

  const getStorageKey = (problemId, selectedLanguage) => `${problemId}-${selectedLanguage}`;

  useEffect(() => {
    const savedCode = localStorage.getItem(getStorageKey(problemId, selectedLanguage));
    setCode(savedCode || templateOptions[selectedLanguage]);
  }, [problemId, selectedLanguage]);

  const handleRunCode = async () => {
    setIsExecuting(true);
    setExecutionResult(null);
    setError(null);
    
    try {
      const response = await axios.post('http://localhost:8082/api/execute', {
        code: code,
        language: selectedLanguage,
        problemId: problemId,
        testCases: {} // Add test cases if available
      });
      
      setExecutionResult(response.data);
    } catch (err) {
      setError(err.response?.data?.error || 'An error occurred while executing the code');
      console.error('Error executing code:', err);
    } finally {
      setIsExecuting(false);
    }
  };

  return (
    <div className="p-4">
      {/* Language Selector */}
      <div className="mb-4">
        <label
          htmlFor="language-select"
          className="block text-md font-semibold text-blue-700 mb-2"
        >
          Select Language
        </label>
        <select
          id="language-select"
          value={selectedLanguage}
          onChange={(e) => {
            const newLanguage = e.target.value;
            setSelectedLanguage(newLanguage);
            setCode(templateOptions[newLanguage]); // Update the code template for the new language
          }}
          className="w-48 p-2 rounded-full border border-indigo-400 bg-indigo-50 text-indigo-700 shadow-md hover:bg-indigo-100 focus:outline-none focus:ring-2 focus:ring-indigo-100 focus:border-indigo-100 transition-all duration-200"
        >
          {Object.keys(languageOptions).map((lang) => (
            <option key={lang} value={lang}>
              {lang}
            </option>
          ))}
        </select>
      </div>

      {/* Code Editor */}
      <CodeMirror
        value={code}
        height="500px"
        extensions={[languageOptions[selectedLanguage]()]} // Call the function here
        theme={oneDark}
        onChange={
          (value) => {
            setCode(value)
            localStorage.setItem(getStorageKey(problemId, selectedLanguage), value)
          }
        }
      />

      {/* Run Code Button */}
      <div className="mt-4">
        <button
          onClick={handleRunCode}
          disabled={isExecuting}
          className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-opacity-50 disabled:opacity-50"
        >
          {isExecuting ? 'Running...' : 'Run Code'}
        </button>
      </div>

      {/* Execution Results */}
      {executionResult && (
        <div className="mt-4 p-4 border rounded-md bg-gray-800 text-white">
          <h3 className="text-lg font-semibold mb-2">Execution Results</h3>
          <div className="mb-2">
            <span className="font-medium">Status: </span>
            <span className={executionResult.success ? 'text-green-400' : 'text-red-400'}>
              {executionResult.success ? 'Success' : 'Failed'}
            </span>
          </div>
          <div className="mb-2">
            <span className="font-medium">Execution Time: </span>
            <span>{executionResult.executionTime} ms</span>
          </div>
          {executionResult.output && (
            <div className="mb-2">
              <span className="font-medium">Output:</span>
              <pre className="mt-1 p-2 bg-gray-900 rounded-md overflow-auto whitespace-pre-wrap">
                {executionResult.output}
              </pre>
            </div>
          )}
          {executionResult.error && (
            <div className="mb-2">
              <span className="font-medium">Error:</span>
              <pre className="mt-1 p-2 bg-gray-900 text-red-400 rounded-md overflow-auto whitespace-pre-wrap">
                {executionResult.error}
              </pre>
            </div>
          )}
        </div>
      )}

      {/* Error Display */}
      {error && (
        <div className="mt-4 p-4 border border-red-500 rounded-md bg-red-100 text-red-700">
          <h3 className="text-lg font-semibold mb-2">Error</h3>
          <p>{error}</p>
        </div>
      )}
    </div>
  );
};

export default CodeEditor;
