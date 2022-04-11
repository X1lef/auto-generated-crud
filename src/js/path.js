class Path {
    static fileContentPath = "src/file_content";

    constructor(model, destination) {
        this.model = model;
        this.destination = destination;
    }

    static list(nameFile) {
        return [
            new Path(`${Path.fileContentPath}/Controller.java`, `${Path.fileContentPath}/test/controller/${nameFile}API.java`),
            new Path(`${Path.fileContentPath}/ServiceImpl.java`, `${Path.fileContentPath}/test/service/${nameFile}ServiceImpl.java`)
        ];
    }

    toString() {
        return `Model: ${this.model}, Destination: ${this.destination}`;
    }
}

module.exports = Path;