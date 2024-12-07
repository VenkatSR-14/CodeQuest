import React, {useState} from 'react';
import apiClient from './apiClient';


export const getProblemTopics = async() => {
    try{
    const response = await apiClient.get('/problem-topics');
    return response.data
    }catch(error){
        console.error("There was an error in fetching topics");
        throw error;
    }


}