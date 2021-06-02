"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SnpImputation = void 0;
const util_1 = require("./util");
class SnpImputation {
    performSubmitAction(formData) {
        console.log(formData);
        const result = util_1.objectArrayToJsonStr(formData);
        console.log(result);
        return result;
    }
}
exports.SnpImputation = SnpImputation;
