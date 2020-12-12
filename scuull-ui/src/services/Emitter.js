import EventEmitter from 'eventemitter3';

/*
 * This sample comes from https://medium.com/@krzakmarek88/eventemitter-instead-of-lifting-state-up-f5f105054a5.
 */
const eventEmitter = new EventEmitter();

/**
 * Hooks into the EventEmitter framework.
 */
const Emitter = {

    LOGON: 'USER_LOGIN',
    LOG_OFF: 'USER_LOGOFF',

    /**
     * Starts listening for an event. Every call to this function should have a corresponding call to off.
     *
     * @param event The event to start listening for.
     * @param fn The function to call when the event is triggered.
     * @returns {EventEmitter<string | symbol, any>}
     */
    on: (event, fn) => eventEmitter.on(event, fn),


    /**
     * Listen for one instance of an event.
     *
     * @param event The event to listen for.
     * @param fn The function to call when the event is triggered.
     * @returns {EventEmitter<string | symbol, any>}
     */
    once: (event, fn) => eventEmitter.once(event, fn),

    /**
     * Stops listening for an event.
     *
     * @param event The event to stop listening for.
     * @returns {EventEmitter<string | symbol, any>}
     */
    off: (event) => eventEmitter.off(event),

    /**
     * Issues an event.
     *
     * @param event The event to issue.
     * @param payload Any information that should be included in the event.
     * @returns {boolean}
     */
    emit: (event, payload) => eventEmitter.emit(event, payload)
}

Object.freeze(Emitter);

export default Emitter;