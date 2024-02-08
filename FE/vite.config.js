import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { VitePWA } from 'vite-plugin-pwa'

// https://vitejs.dev/config/
export default defineConfig({
  workboxOptions: {
    include: [/^index\.html$/, /\.css$/, /\.js$/, /^manifest\.json$/, /\.png$/],
    exclude: []
  },
  publicPath: '/',
  plugins: [
    vue(),
    VitePWA({
      registerType: 'autoUpdate',
      includeAssets: ['favicon.ico', 'robots.txt', 'apple-touch-icon.png', 'masked-icon.svg'],
      manifest: {
        name: 'POP-CON',
        short_name: 'POP-CON',
        icons: [
          {
            src: './public/img/icons/pwa-192x192.png',
            sizes: '192x192',
            type: 'image/png',
            purpose: 'any',
          },
          {
            src: './public/img/icons/pwa-512x512.png',
            sizes: '512x512',
            type: 'image/png',
            purpose: 'any',
          },
          {
            src: './public/img/icons/pwa-maskable-192x192.png',
            sizes: '192x192',
            type: 'image/png',
            purpose: 'maskable',
          },
          {
            src: './public/img/icons/pwa-maskable-512x512.png',
            sizes: '512x512',
            type: 'image/png',
            purpose: 'maskable',
          },
        ],
        start_url: '/',
        display: 'standalone',
        background_color: '#ffffff',
        theme_color: '#ffffff',
      },
      workbox: {
        globPatterns: ['**/*.{js,css}', 'index.html'],
        runtimeCaching: [
          {
            urlPattern: /\.png$/,
            handler: 'CacheFirst',
            options: {
              cacheName: 'png-cache',
              expiration: {
                maxEntries: 10,
                maxAgeSeconds: 60 * 60 * 24 * 365,
              },
            },
          },
          {
            urlPattern: /\.json$/,
            handler: 'StaleWhileRevalidate',
            options: {
              cacheName: 'json-cache',
              cacheableResponse: {
                statuses: [200],
              },
            },
          },
        ],
      },
    }),
  
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  optimizeDeps: {
    entries: [],
}
})
