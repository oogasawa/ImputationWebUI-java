

import  create  from 'https://raw.githubusercontent.com/r3wt/object-path/master/mod.ts';


export type ComponentInfo = {
    name: string;
    value: any;
}


export class FormData {


    objectArrayToJson(info: ComponentInfo[]): string {

        const {set} = create({
            seperator: '.',
            defaultUnsetValue: undefined,
            autoCreate: true
        })

        const result: object = {};

        for (let i = 0; i < info.length; i++) {
            set(result, info[i].name, info[i].value);
        }

        return JSON.stringify(result);
    }


}

