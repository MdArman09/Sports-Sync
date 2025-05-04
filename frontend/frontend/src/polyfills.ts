(window as any).global = window;
globalThis.Buffer = globalThis.Buffer || require('buffer').Buffer;
globalThis.process = require('process');
