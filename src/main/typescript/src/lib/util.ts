
import objectPath from "object-path";

export interface FormData {
    name: string;
    value: string;
}

export function objectArrayToJsonStr(objArray: FormData[]): string {
    let obj: any = {};

    for (let i = 0; i < objArray.length; i++) {
        objectPath.set(obj, objArray[i].name, objArray[i].value);
    }

    return JSON.stringify(obj);
}
