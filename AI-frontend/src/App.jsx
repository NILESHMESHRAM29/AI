import { useState } from 'react';
import './App.css';
import ChatInput from './components/ChatInput';
import ChatResponse from './components/ChatResponse';
import { fetchChatResponse } from './services/api';

function App() {
  const [response, setResponse] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleQuestionSubmit = async (question) => {
    setLoading(true);
    setResponse(null);
    try {
      const apiResponse = await fetchChatResponse(question);
      setResponse(apiResponse);
    } catch (error) {
      alert('Failed to get response');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className='cyber-app'>
      <header className='cyber-header'>
        <h1>⚡ Gemini AI ⚡</h1>
      </header>
      
      <div className='chat-area'>
        <ChatResponse response={response} loading={loading} />
        <ChatInput onSubmit={handleQuestionSubmit} />
      </div>
    </div>
  );
}

export default App;
