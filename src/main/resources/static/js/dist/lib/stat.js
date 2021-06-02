import objectPath from "object-path";
export function freq(data) {
    let result = {};
    let item;
    for (item in data) {
        let num = 0;
        if (objectPath.has(result, item)) {
            num = objectPath.get(result, item);
        }
        objectPath.set(result, item, ++num);
    }
    return result;
}
