"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.objectArrayToJsonStr = void 0;
const object_path_1 = __importDefault(require("object-path"));
function objectArrayToJsonStr(objArray) {
    let obj = {};
    for (let i = 0; i < objArray.length; i++) {
        object_path_1.default.set(obj, objArray[i].name, objArray[i].value);
    }
    return JSON.stringify(obj);
}
exports.objectArrayToJsonStr = objectArrayToJsonStr;
