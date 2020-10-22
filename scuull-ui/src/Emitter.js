import EventEmitter from 'eventemitter3';

/*
 * This sample comes from https://medium.com/@krzakmarek88/eventemitter-instead-of-lifting-state-up-f5f105054a5.
 */
const eventEmitter = new EventEmitter();

/**
 * Hooks into the EventEmitter framework.
 */
const Emitter = {
    // Use this when you want to turn on listening for an event.
    on: (event, fn) => eventEmitter.on(event, fn),
    // Use this to only listen to an event once.
    once: (event, fn) => eventEmitter.once(event, fn),
    // Use this to stop listening for an event. Each on should have a corresponding off.
    off: (event, fn) => eventEmitter.off(event, fn),
    // Use this to issue an event.
    emit: (event, payload) => eventEmitter.emit(event, payload)
}

Object.freeze(Emitter);

export default Emitter;