function createObjectPathInstance(options) {
    const config = {
        seperator: '.',
        defaultUnsetValue: undefined,
        autoCreate: false
    };
    const configure = (options1 = {
    })=>{
        for(let k in config){
            if (options1.hasOwnProperty(k)) {
                config[k] = options1[k];
            }
        }
        return config;
    };
    const parse = (path)=>{
        return path.split(config.seperator);
    };
    const set = (obj, path, value)=>{
        const p = parse(path);
        if (p.length) {
            let o = obj;
            for(let i = 0; i < p.length - 1; i++){
                if (o !== null && typeof o === 'object') {
                    if (!o.hasOwnProperty(p[i])) {
                        if (config.autoCreate) {
                            o[p[i]] = {
                            };
                            o = o[p[i]];
                            continue;
                        } else {
                            return false;
                        }
                    } else {
                        o = o[p[i]];
                    }
                } else {
                    return false;
                }
            }
            o[p[p.length - 1]] = value;
            return true;
        }
        return false;
    };
    const get = (obj, path)=>{
        const p = parse(path);
        let v = obj;
        for(let i = 0; i < p.length; i++){
            if (v !== null && typeof v === 'object' && v.hasOwnProperty(p[i])) {
                v = v[p[i]];
            } else {
                return config.defaultUnsetValue;
            }
        }
        return v;
    };
    const module = {
        configure,
        parse,
        set,
        get
    };
    if (options) {
        configure(options);
    }
    return module;
}
class FormData1 {
    objectArrayToJson(info) {
        const { set  } = createObjectPathInstance({
            seperator: '.',
            defaultUnsetValue: undefined,
            autoCreate: true
        });
        const result = {
        };
        for(let i = 0; i < info.length; i++){
            set(result, info[i].name, info[i].value);
        }
        return JSON.stringify(result);
    }
}
export { FormData1 as FormData };
