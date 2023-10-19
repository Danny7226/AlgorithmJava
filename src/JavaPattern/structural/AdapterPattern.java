package JavaPattern.structural;

/**
 * Adaptor is about provide consistent client interface by having an
 * adapting logic in middle with the actual concrete implementation class
 */
public class AdapterPattern {
    interface ClientInterface {
        int criticalNumber();
    }

    class InternalClass {
        int someNumber() {
            return 100;
        }
    }

    class InternalClassAdaptor implements ClientInterface {
        private final InternalClass internalClass;
        public InternalClassAdaptor(final InternalClass internalClass) {
            this.internalClass = internalClass;
        }

        @Override
        public int criticalNumber() {
            final int someAdaptingNumber = 10_000;
            return internalClass.someNumber() * someAdaptingNumber;
        }
    }
}
