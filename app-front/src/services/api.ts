import axios from 'axios';

export const api = axios.create({
  baseURL: 'https://vendasonline-pjb0.onrender.com/api/v1',
});