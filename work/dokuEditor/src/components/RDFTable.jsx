import _       from "lodash/fp";
import React   from "react";
import {Table} from "react-bootstrap";

import RDFTableRow from "./RDFTableRow.jsx";

export default function ({entries}) {
    const rows = _(entries)
        .map(({id, ...rest}) => <RDFTableRow key={id} {...rest} />)
        .value();

    return (
        <Table striped bordered responsive>
            <thead>
                <tr>
                    <th>Subject</th>
                    <th>Predicate</th>
                    <th>Type</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>{rows}</tbody>
        </Table>
    );
}