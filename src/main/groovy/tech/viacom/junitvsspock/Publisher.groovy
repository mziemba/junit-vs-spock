package tech.viacom.junitvsspock

class Publisher {
    List<Subscriber> subscribers = []

    void send(String message) {
        subscribers.forEach { it.receive(message) }
    }
}
