import React, {useState} from 'react';
import { Editor } from '@monaco-editor/react';

const CodeEditor = () => {
  return (
    <Editor
      height="600px"
      defaultLanguage="python"
      defaultValue="# Write your code here..."
      theme="vs-dark"
      options={{
        fontSize: 14,
        minimap: { enabled: false },
      }}
    />
  );
};

export default CodeEditor;
