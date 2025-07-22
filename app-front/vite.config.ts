import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api/v1': {
        target: 'https://vendasonline-pjb0.onrender.com/api/v1',
        changeOrigin: true,
        secure: false
      }
    }
  }
})