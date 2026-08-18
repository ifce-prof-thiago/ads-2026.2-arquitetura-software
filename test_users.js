import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 100 },
    { duration: '30s', target: 250 },
    { duration: '30s', target: 500 },
    { duration: '30s', target: 750 },
    { duration: '30s', target: 1000 },

    // Mantém 1.000 usuários simultâneos
    { duration: '2m', target: 1000 },

    // Reduz gradualmente
    { duration: '30s', target: 750 },
    { duration: '30s', target: 500 },
    { duration: '30s', target: 250 },
    { duration: '30s', target: 0 },
  ],

  thresholds: {
    http_req_failed: ['rate<0.05'],
    http_req_duration: ['p(95)<1000', 'p(99)<2000'],
  },
};

const BASE_URL = 'http://localhost:8080';

export default function () {
  const response = http.get(`${BASE_URL}/api/v1/users`);

  check(response, {
    'status é 200': (r) => r.status === 200,
    'resposta não está vazia': (r) => r.body && r.body.length > 0,
  });

  sleep(1);
}