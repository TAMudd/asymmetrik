## Asymmetrik Programming Challenge - Mobile Device Keyboard

#### Build it
`mvn clean package`

#### Run it
`java -jar target/autocomplete-1.0-SNAPSHOT-jar-with-dependencies.jar`

#### Test it

At the prompt:

* To train the provider, type the letter 'T' and press Enter. You will then get a `Train:` prompt. Type a training string and press Enter.

* To input a fragment for autocomplete suggestions, type 'I' and press Enter. You will then get an `Input:` prompt. Type a word fragment and press Enter to recieve a list of potential completions sorted by confidence based on previous training.

* To quit, type 'Q' and press Enter.
