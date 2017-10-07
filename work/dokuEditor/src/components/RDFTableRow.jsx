import React from "react";

export default function ({data_s, data_p, data_t, data_d}) {
    return (
        <tr>
            <td>{data_s}</td>
            <td>{data_p}</td>
            <td>{data_t}</td>
            <td>{data_d}</td>
        </tr>
    );
}