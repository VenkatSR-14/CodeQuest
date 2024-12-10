import React, { useState, useEffect } from 'react';
import CodeMirror from '@uiw/react-codemirror';
import { javascript } from '@codemirror/lang-javascript';
import { python } from '@codemirror/lang-python';
import { java } from '@codemirror/lang-java';
import { cpp } from '@codemirror/lang-cpp';
import { oneDark } from '@codemirror/theme-one-dark';
import { useParams } from 'react-router-dom';

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

  const getStorageKey = (problemId, selectedLanguage) => `${problemId}-${selectedLanguage}`;

  useEffect(() => {
    const savedCode = localStorage.getItem(getStorageKey(problemId, selectedLanguage));
    setCode(savedCode || templateOptions[selectedLanguage]);
  }, [problemId, selectedLanguage]);

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
            localStorage.setItem(getStorageKey(problemId, selectedLanguage), code)
          }
        }
      />
    </div>
  );
};

export default CodeEditor;
