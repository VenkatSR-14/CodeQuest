import React, {useState, useEffect} from 'react';
import { getProblemTopics } from '../../api/dashboardService';
import './ProblemTopics.css'

export const ProblemTopics = () => {
    const [loading, setLoading] = useState(true);
    const [topics, setTopics] = useState([]);
    const [error, setError] = useState(null);


    useEffect(() => {
        const fetchTopics = async () => {
            try{
                setLoading(true);
                const data = await getProblemTopics();
                setTopics(data)
            }catch (err){
                setError(err.message);
            }finally{
                setLoading(false);
            }
        };
        fetchTopics();
    }, []);

    return (
        <div className="min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center px-4">
            {loading && (
                <div className="flex justify-center items-center">
                    <div className="w-12 h-12 border-4 border-gray-300 border-t-transparent rounded-full animate-spin"></div>
                </div>
            )}
            {!loading && error && (
                <div className="text-red-500 text-lg font-semibold mt-4">
                    Error loading topics: {error}
                </div>
            )}
            {!loading && !error && (
                <div className="text-center">
                    <h1 className="text-3xl font-bold mb-6">Problem Topics</h1>
                    <ul className="space-y-3">
                        {topics.map((topic, index) => (
                            <li
                                key={index}
                                className="bg-gray-800 p-4 rounded-lg shadow hover:bg-gray-700 transition"
                            >
                                {topic}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default ProblemTopics;