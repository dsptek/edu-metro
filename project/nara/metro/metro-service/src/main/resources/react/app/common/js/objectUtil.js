
const objectUtil = {
  //
  toNameValues(object, filters) {
    //
    const nameValueList = {
      nameValues: Object.keys(object).map((key) => {
        //
        let value = object[key];

        if (typeof value === 'number' || typeof value === 'boolean') {
          value = value.toString();
        }
        else if (typeof value === 'object' && value !== null) {
          value = JSON.stringify(value);
        }
        return {
          name: key,
          value: value,
        };
      }),
    };

    if (Array.isArray(filters)) {
      nameValueList.nameValues = nameValueList.nameValues.filter((nameValue) => filters.includes(nameValue.name));
    }

    return nameValueList;
  }
};

export default objectUtil;


